from flask import Flask, render_template, flash, redirect, session, request, g, make_response
from flask_wtf import Form
from wtforms import TextField
from wtforms.validators import DataRequired
import json
from app import app, database, block
from utils import *

class ImageSetting(Form):
    src = TextField('src')

class Image:
    def __init__(self):
        a = 0
        
    def render(self, config):
        urls = json.loads(config)
        return render_template("Image.html", urls = urls)

    def getform(self):
        form = ImageSetting()
        return form

    def rendersetting(self, config, blockId):
        urls = json.loads(config)
        return render_template("ImageSetting.html", urls = urls, blockId = blockId, num = len(urls))

    def applysetting(self, blockId):
        fetch = [i for i in g.blocks if i.uid == int(blockId[1:])]
        if len(fetch) == 0 : return "Can't find block in session!"
        b = fetch[0]
        print b.config[1]
        blocks = json.loads(session['ihomepage'])
        which = 0
        for i in range(0, len(blocks)):
            if blocks[i]['uid'] == int(blockId[1:]):
                which = i
        print json.dumps(blocks[which]['config'])
        urls = b.config
        print type(b.config)
        if (type(b.config) == type("string")):
            urls = json.loads(b.config)
        if (request.form.has_key('change')):
            for i in range(0, len(urls)):
                urls[i] = str(request.form.get('src_'+str(i)))
            blocks[which]['config'] = urls
            session['ihomepage'] = json.dumps(blocks)
            return 'success'
        elif (request.form.has_key('add')):
            urls.append(request.form.get('srcadd'))
            b.config = json.dumps(urls)
            return 'success'
        return 'fail'
            
