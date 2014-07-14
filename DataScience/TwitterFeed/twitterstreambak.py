'''
Created on Jul 7, 2014

@author: v-jcoov
'''
import oauth2 as oauth

print "Hello"

api_key = " l8dwlBReZF1u486Dwqrocxyao" ##Consumer key
api_secret = " WJxhTNttaz6X6F4bBZuVAxdf3EF3GA1XmVvqIpyXOm3DC6bZZ5" #Consumer secret
access_token_key = "48122554-hccXpeqAc0F6sx42yxikNAFFfcfnV7ee4NBhoNbxG"
access_token_secret = "7LdzULIhxbj6ByXEPPSqTmTknW9U9P1c09adMQokv4d8U"

def oauth_req(url, key, secret, http_method="GET", post_body=None, http_headers=None):
    consumer = oauth.Consumer(key=api_key, secret=api_secret)
    token = oauth.Token(key=key, secret=secret)
    client = oauth.Client(consumer, token)

    content = client.request("http://www.jaycoover.com", method="GET", body="track=twitter", headers=http_headers, force_auth_header=True)

    return content

home_timeline = oauth_req('https://stream.twitter.com/1.1/statuses/filter.json', access_token_key, access_token_secret)

print home_timeline