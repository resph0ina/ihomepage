# -*- coding: utf-8 -*-
import urllib, urllib2, socket, cookielib
import json
from BeautifulSoup import BeautifulSoup

class BaiduWordGrabber(object):
	"""docstring for BaiduWordGrabber"""
	def grab(self, arg = {}):
		url = "http://news.baidu.com/"
		page = urllib2.urlopen(url)
		soup = BeautifulSoup(page,fromEncoding='gbk')
		hotwords = soup.find('ul',{"class":"hotwords"})
		data = list()		
		hotword = hotwords.findAll('li',)
		for item in hotword:
			i = dict()
			focus = item.find('a',{"class":"detail"})
			i['text'] = focus.text
			i['url'] = focus.get('href')
			data.append(i)
		return {'name':u'百度热搜词','url':'http://news.baidu.com',
			'type':'textlines','data':data}

