# -*- coding: utf-8 -*-
import urllib, urllib2, socket, cookielib
import json
from BeautifulSoup import BeautifulSoup

class WeatherGrabber(object):
	"""docstring for WeatherGrabber"""
	def grab(self, *arg):
		html = urllib2.urlopen('http://www.weather.com.cn/data/sk/101010100.html', timeout=5).read()
		d = json.loads(html)['weatherinfo']
		res = []
		res.append({'text':d['city']})
		res.append({'text':d['temp'] + u'℃'})
		res.append({'text':d['WD'] + ' ' + d['WS']})
		return {'name':u'实时天气情况','url':'http://www.weather.com.cn/html/weather/101010100.shtml',
		'type':'textlines','data':res}

	def __init__(self):
		pass