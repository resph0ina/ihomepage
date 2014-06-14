# -*- coding: utf-8 -*-
import urllib, urllib2, socket, cookielib
import json
from BeautifulSoup import BeautifulSoup

class ZhihuGrabber(object):
	"""docstring for ZhihuGrabber"""
	def grab(self, arg = {}):
		cookie_support= urllib2.HTTPCookieProcessor(cookielib.CookieJar())
		opener = urllib2.build_opener(cookie_support, urllib2.HTTPHandler)
		urllib2.install_opener(opener)
		content = urllib2.urlopen('http://www.zhihu.com/').read()
		postdata=urllib.urlencode({
			'_xsrf':'62e55b3802ca44aa9979f98fbfe12e75',
			'email':arg['username'],
			'password':arg['password'],
			'rememberme':'y'
			})
		req = urllib2.Request(url = 'http://www.zhihu.com/login',data = postdata)
		result = urllib2.urlopen(req)
		soup = BeautifulSoup(result)
		question_link = soup.findAll('a',{"class":"question_link"})
		data = list()		
		for item in question_link:
			i = dict()
			i['text'] = item.text
			i['url'] = 'http://www.zhihu.com'+item.get('href')
			data.append(i)
		return {'name':u'知乎 感兴趣的','url':'http://www.zhihu.com/',
			'type':'textlines','data':data}
