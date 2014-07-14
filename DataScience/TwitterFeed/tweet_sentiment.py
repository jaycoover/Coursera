import sys
import json

def hw():
    print 'Hello, world!'

def lines(fp):
    print str(len(fp.readlines()))


    
def main():
    print sys.argv[0]
    print sys.argv[1]
    print sys.argv[2]
    sent_file = open(sys.argv[1])
    #tweet_file = open(sys.argv[2])
    with open(sys.argv[2]) as f:
      for line in f:
        tweet = json.loads(line)
        if tweet.has_key('text') and tweet['lang']=="en":
          print tweet['text'].encode('utf-8')
    #print tweetlist
    #print tweet_file
    #jsonObj = json.load(text)
    #lines(sent_file)
    #lines(tweet_file)

    scores = {} # initialize an empty dictionary
    for line in sent_file:
      term, score  = line.split("\t")  # The file is tab-delimited. "\t" means "tab character"
      scores[term] = int(score)  # Convert the score to an integer.
      #print term + ": " + str(scores[term])
    
    
    
    #print tweets
    #print scores.items() # Print every (term, score) pair in the dictionary
    
if __name__ == '__main__':
    main()
