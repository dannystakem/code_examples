"""
Program that checks the time remaining on all ssl certs 
across AWS accounts, and informs the user by email through 
an SNS topic if any certs are coming close to expiration
"""

import sys
import boto3
import socket
import ssl
import re
import os
import datetime


def get_certs_for_account(account_role):
    session_assumed = assume_role(
        role=account_role,
        session_name='crossRoleSession'
        )
    try:
        response = session_assumed.client('acm').list_certificates(
            CertificateStatuses=[
                'PENDING_VALIDATION',
                'ISSUED',
                'INACTIVE',
                'EXPIRED',
                'VALIDATION_TIMED_OUT',
                'REVOKED',
                'FAILED'
            ],
            MaxItems=500)
        certs = response.get('CertificateSummaryList')
    except:
        return None,
    return certs


def ssl_valid_time_remaining(domain_name):
    expires = ssl_expiry_date(domain_name)
    return expires - datetime.datetime.utcnow().date()


def ssl_expiry_date(domain_name):
    ssl_date_format = r'%b %d %H:%M:%S %Y %Z'
    context = ssl.create_default_context()

    conn = context.wrap_socket(
        socket.socket(socket.AF_INET),
        server_hostname=domain_name,
    )

    conn.settimeout(3.0)
    conn.connect((domain_name, 443))
    ssl_info = conn.getpeercert()
    return datetime.datetime.strptime(
        ssl_info['notAfter'],
        ssl_date_format
    ).date()


def create_domain_name_list(account_role):
    domain_name_list = []
    certs = get_certs_for_account(account_role)

    # account for wildcard ssl certs
    for cert_info in certs:
        if re.search(r'\*', cert_info.get('DomainName')):
            domain_name_list.append(
                cert_info.get('DomainName').replace('*', '<value>')
            )
        else:
            domain_name_list.append(cert_info.get('DomainName'))

    return domain_name_list


def send_sns_alert(domain_name, expiry_days, ssl_status):
    session_default = assume_role()
    SNS_ARN = os.environ['snsArn']
    client = boto3.client('sns')
    sslStat = domain_name + ' SSL certificate will expire in ' + expiry_days + ' days!!! '
    snsSub = domain_name + ' SSL Certificate Expiry ' + ssl_status + ' Alert'

    response = client.publish(
        TargetArn=SNS_ARN,
        Message=sslStat,
        Subject=snsSub
    )


def assume_role(role=None, session_name='default'):
    if role:
        client = boto3.client('sts')
        response = client.assume_role(
            RoleArn=role,
            RoleSessionName=session_name
        )

        session = boto3.Session(
            aws_access_key_id=response['Credentials']['AccessKeyId'],
            aws_secret_access_key=response['Credentials']['SecretAccessKey'],
            aws_session_token=response['Credentials']['SessionToken']
        )
        return session
    else:
        return boto3.Session()


def main(event, context):
    customer_account_one = create_domain_name_list(os.environ['<customer1>'])
    customer_account_two = create_domain_name_list(os.environ['<customer2>'])
    common_names = customer_account_one + customer_account_two
    for domain_name in common_names:
        expDate = ssl_valid_time_remaining(domain_name.strip())
        (a, b) = str(expDate).split(',')
        (c, d) = a.split(' ')

        if int(c) < 50:
            send_sns_alert(domain_name, str(c), 'Warning')
        else:
            print('{} - Everything is fine - {} days remaining on cert'.format(domain_name, c))


if __name__ == "__main__":
    main()
