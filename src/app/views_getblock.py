from flask import Flask, render_template, flash, redirect, session, request, g, make_response
from app import app, database, block
from modules import *
import json, urllib2

services = {'baidu.news': TestGrabber.TestGrabber(), 'renren.status': RenrenGrabber.RenrenGrabber()}

@app.route('/getblock/<blockId>', methods = ['GET', 'POST'])
def getblock(blockId):
	# b = block.Block(1,1,'test')
	fetch = [i for i in g.blocks if i.uid == int(blockId[1:])]
	if len(fetch) == 0 : return "Can't find block in session!"
	b = fetch[0]
	if services.has_key(b.name):
		b.content['raw'] = json.loads(getservice(b.name))
	else:
		b.content['raw'] = 'Default'
	return render_template('block_textlines.html', block = b)

@app.route('/service/')
def infoservice():
	return 'Supported services: ' + repr(services.keys());

@app.route('/service/<name>', methods = ['GET', 'POST'])
def getservice(name):
	if services.has_key(name):
		grabber = services[name]
		if request.form.has_key('username'):
			return json.dumps(grabber.grab(request.form['username'], request.form['password']))
		return json.dumps(grabber.grab())
	else:
		return infoservice()