from PIL import Image
import glob, os

size = 192,192

for infile in glob.glob("TeamPhotos/*.jpg"):
    file, ext = os.path.splitext(infile)
    head, tail = os.path.split (file)
    im = Image.open(infile)
    im.thumbnail(size, Image.ANTIALIAS)
    file = os.path.join ("images", tail)
    if not os.path.isdir("images"):
        os.mkdir ("images")
    im.save(file + ".pcx")
