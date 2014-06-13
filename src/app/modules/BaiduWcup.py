# -*- coding: utf-8 -*-
import urllib, urllib2, socket, cookielib
import json
from BeautifulSoup import BeautifulSoup

class BaiduWorldcupGrabber(object):
	"""docstring for BaiduWorldcupGrabber"""
	def grab(self, arg = {}):
		url = "http://news.baidu.com/"
		page = urllib2.urlopen(url)
		soup = BeautifulSoup(page,fromEncoding='gbk')
		todaystars = soup.find('ul',{"id":"today-star"})
		data = list()
		todaystar = todaystars.findAll('a',)
		for item in todaystar:
			i = dict()
			i['text'] = item.text
			i['image'] = item.img.get('src')
			i['url'] = item.get('href')
			data.append(i)
		return {'name':u'世界杯今日明星','url':'http://2014.baidu.com/',
			'type':'imgtextlines','data':data}
