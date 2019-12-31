import boto3
import argparse
from botocore.exceptions import ClientError


def list_resource_tags():
    tag_group_list = []
    arn_group_list = []

    # iterate through aws regions
    for reg in regions:
        client = boto3.client('resourcegroupstaggingapi', region_name=reg)
        try:
            response = client.get_resources()
        except ClientError as e:
            print("Unexpected error: %s" % e)

        # create lists of resource arns and tags, resources must already have a tag/s, or they won't be picked up
        while True:
            page_token = response["PaginationToken"]
            arn_group_list = arn_group_list + [x['ResourceARN'] for x in response['ResourceTagMappingList']]
            tag_group_list = tag_group_list + [x['Tags'] for x in response['ResourceTagMappingList']]
            if page_token == "":
                break
            response = client.get_resources(PaginationToken=page_token)

    index = 0
    while index < len(arn_group_list):
        print('==== ResourceArn ==========================================================================')
        print(arn_group_list[index])
        print('==== Tags =================================================================================')
        print(tag_group_list[index])
        print('\n')
        index += 1


def tag_resources():
    arn_group_list = []

    for reg in regions:
        client = boto3.client('resourcegroupstaggingapi', region_name=reg)
        try:
            response = client.get_resources()
        except ClientError as e:
            print("Unexpected error: %s" % e)

        while True:
            page_token = response["PaginationToken"]
            arn_group_list = arn_group_list + [x['ResourceARN'] for x in response['ResourceTagMappingList']]
            if page_token == "":
                break
            response = client.get_resources(PaginationToken=page_token)

    # iterate through resource arns and add tags
    for id in arn_group_list:
        response = client.tag_resources(
            ResourceARNList=[id, ],
            Tags={
                'Application ID': '<?>',
                'Application Role': '<?>',
                'Business Unit': '<?>',
                'Environment': '<?>'
            }
        )
        print('Added Tags to Resource: {0}'.format(id))
        print(response)


if __name__ == '__main__':
    client = boto3.client('ec2')
    # create a list of aws regions
    regions = [region['RegionName'] for region in client.describe_regions()['Regions']]
    page_token = ""

    parser = argparse.ArgumentParser()
    parser.add_argument(
        '--list_tags',
        help='list the resource ARNs and tags',
        action='store_true'
    )
    parser.add_argument(
        '--add_tags',
        help='add tags to a resource using the resource ARNs',
        action='store_true'
    )
    args = parser.parse_args()

    if args.list_tags:
        list_resource_tags()
    if args.add_tags:
        tag_resources()
