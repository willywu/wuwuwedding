import httplib, urllib, urllib2
from optparse import OptionParser

HOST = "http://localhost:9000"
VOTE_PATH = "/guests/new"

def open_url_via_httplib(guest_one, guest_two, has_extra_guest, rsvp_code):
    post_data = {"guestOneNameDouble": guest_one,
                 "guestTwoNameDouble": guest_two,
                 "hasExtraGuest": has_extra_guest,
                 "rsvpCode": rsvp_code}
    encoded_data = urllib.urlencode(post_data)
    host_no_scheme = HOST.split("://")[1]
    con = httplib.HTTPConnection(host_no_scheme)
    headers = {"Content-type": "application/x-www-form-urlencoded",
               "Accept": "text/plain"}
    con.request("POST", VOTE_PATH, encoded_data, headers)
    resp = con.getresponse()
    body = resp.read()
    print("body %s" % body)
    location = resp.getheader('Location')
    print("location %s" % location)
    return location

def insert_guest(line):
    guest_one, guest_two, has_extra_guest, rsvp_code = line.split(",")
    print("Inserting %s" % guest_one)
    try:
        open_url_via_httplib(guest_one, guest_two, has_extra_guest, rsvp_code)
        return True
    except (urllib2.HTTPError, urllib2.URLError) as e:
        print("Failed")
    return False

if __name__=="__main__":
    in_filename = "guests.csv"
    with open(in_filename, 'r') as in_file:
        for line in in_file:
            insert_guest(line.strip())
