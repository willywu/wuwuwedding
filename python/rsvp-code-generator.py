import random

"""Generate unique RSVP codes for the wedding"""

if __name__=="__main__":
    chars = "BCDEFGHJKLMNPQRTVWXY346789" # exclude (0,O), (1,I), (2,Z), (5,S)
    items = [x for x in chars]

    out_filename = "codes.txt"
    with open(out_filename, 'w') as out_file:
        codes = []
        for i in range(100):
            codes.append(''.join([random.choice(items) for i in range(5)])) # codes are 8 chars long
        codes.sort()
        out_file.write('\n'.join(codes))