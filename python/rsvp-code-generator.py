import random

"""Generate unique RSVP codes for the wedding"""

if __name__=="__main__":
    chars = "BCDFGHJKLMNPQRSTVWXYZ23456789"
    items = [x for x in chars]

    out_filename = "codes.txt"
    with open(out_filename, 'w') as out_file:
        for i in range(100):
            code = ''.join([random.choice(items) for i in range(8)]) # codes are 8 chars long
            out_file.write(code+"\n")