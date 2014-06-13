# -*- coding: utf-8 -*-

class Douban(object):
	"""docstring for Douban"""
	def grab(self, arg = {}):
		return {'name':u'豆瓣','url':'http://douban.fm/',
			'type':'raw','data':'<object width="121" height="458"> <param name="movie" value="http://t.douban.com/swf/radio_widget.swf"></param> <param name="allowscriptaccess" value="always"></param> <param name="wmode" value="opaque"></param> <param name="flashVars" value="doubanid=57840739&maxresults=3"></param> <embed src="http://t.douban.com/swf/radio_widget.swf" type="application/x-shockwave-flash" allowscriptaccess="always" wmode="opaque" width="121" height="458" flashVars="doubanid=57840739&maxresults=3"> </embed> </object>'}
		
