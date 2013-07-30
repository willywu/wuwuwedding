#! /usr/bin/python

import os, sys

if __name__=='__main__':
    fin = open("../public/css/wedding.css")
    for line in fin:
        if "http://" in line:
            line_split = line.split('"')
            for phrase in line_split:
                if phrase.startswith("http://"):
                    cmd = "wget %s" % phrase
                    os.system(cmd)
                    print("Downloaded %s" % phrase)
                    raw_input()