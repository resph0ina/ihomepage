# -*- coding: utf-8 -*-
from flask import Flask, render_template, url_for, redirect, session, request, g, make_response
from app import app, database, block
import json, urllib2
from flask.ext.wtf import Form
from wtforms import TextField, BooleanField, FieldList    

@app.route('/test/')
def test():
	return render_template('block_dynamic.html', content = {'type':'images','data':['http://hdn.xnimg.cn/photos/hdn121/20130504/1530/tiny_Bdgs_ec130002ad5c113e.jpg','http://hdn.xnimg.cn/photos/hdn121/20130504/1530/tiny_Bdgs_ec130002ad5c113e.jpg']})

@app.route('/getblock/<blockId>', methods = ['GET', 'POST'])
def getblock(blockId):
	fetch = [i for i in g.blocks if i.uid == int(blockId[1:])]
	if len(fetch) == 0 : return "Can't find block in session!"
	b = fetch[0]
	if b.name == 'custom':
		b.content = json.loads(urllib2.urlopen(b.config['custom']).read())
	elif app.config['SERVICES'].has_key(b.name):
		b.content = _getservice(b.name, 
			{'username' : b.config['username'], 'password' : b.config['password']})
		#json.loads(getservice(b.name))
	elif app.config['TOOLS'].has_key(b.name):
		return app.config['TOOLS'][b.name].render(blockId)
	else:
		b.content = {'type':'raw','data':'Empty block'}
	print b.content
	return render_template('block_dynamic.html', content = b.content)

@app.route('/service/')
def infoservice():
	return 'Supported services: ' + repr(app.config['SERVICES'].keys());

@app.route('/service/<name>', methods = ['GET', 'POST'])
def getservice(name):
	return json.dumps(_getservice(name, request.form))

def _getservice(name, datadict):
	try:
		if app.config['SERVICES'].has_key(name):
			grabber = app.config['SERVICES'][name]
			return grabber.grab(datadict)
		else:
			return infoservice()
	except Exception, e:
		# raise e
		return {'name':'','type':'raw','data':u'网络错误 ^_^'}

@app.route('/service/getpic')
def getpicture():
	return urllib2.urlopen(request.args.get('url'), timeout = 3).read()
