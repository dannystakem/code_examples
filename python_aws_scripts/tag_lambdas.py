"""
Small script that tags all lambdas in an AWS
account based on stage - dev/sbx/stg/prd
"""

import boto3
import re

client = boto3.client('lambda')
response = client.list_functions()

for i in response['Functions']:
    if re.search(r'dev', i['FunctionArn']):
        tag_response = client.tag_resource(
            Resource=i['FunctionArn'],
            Tags={
                'ProductCode': '<Code>',
                'Function': '<Function>',
                'ServiceLevel': 'DEVELOPMENT',
                'Customers': '<Customer>'
            }
        )
    elif re.search(r'sbx', i['FunctionArn']):
        tag_response = client.tag_resource(
            Resource=i['FunctionArn'],
            Tags={
                'ProductCode': '<Code>',
                'Function': '<Function>',
                'ServiceLevel': 'TEST',
                'Customers': '<Customer>'
            }
        )
    elif re.search(r'stg', i['FunctionArn']):
        tag_response = client.tag_resource(
            Resource=i['FunctionArn'],
            Tags={
                'ProductCode': '<Code>',
                'Function': '<Function>',
                'ServiceLevel': 'UAT',
                'Customers': '<Customer>'
            }
        )
    elif re.search(r'prd', i['FunctionArn']):
        tag_response = client.tag_resource(
            Resource=i['FunctionArn'],
            Tags={
                'ProductCode': '<Code>',
                'Function': '<Function>',
                'ServiceLevel': 'PRODUCTION',
                'Customers': '<Customer>'
            }
        )
    else:
        tag_response = client.tag_resource(
            Resource=i['FunctionArn'],
            Tags={
                'ProductCode': '<Code>',
                'Function': '<Function>',
                'ServiceLevel': 'Internal',
                'Customers': '<Customer>'
            }
        )
