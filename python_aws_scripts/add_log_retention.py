"""
Small script that adds a retention period to Cloudwatch logs in
an AWS account, otherwise they remain indefinitely by default
"""

import boto3
import argparse


def add_log_retention(retention_length):
    client = boto3.client('logs')
    response = client.describe_log_groups()
    log_group_list = []
    while True:
        log_group_list = log_group_list +
        [x['logGroupName'] for x in response['logGroups']]
        if 'nextToken' in response.keys():
            response = client.describe_log_groups(
                nextToken=response['nextToken']
            )
        else:
            break

    for group in log_group_list:
        print('Updating log group {}'.format(group))
        response = client.put_retention_policy(
            logGroupName=group, retentionInDays=retention_length
        )

    print('\nUpdated {} log groups - Added log retention of {} days'.format(
        len(log_group_list), retention_length)
    )

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument(
        '--length', type=int,
        help='Number of days to set Log Retention'
    )
    args = parser.parse_args()
    add_log_retention(args.length)