# -*- coding: utf-8 -*-
class TestGrabber(object):
	"""docstring for TestGrabber"""
	def __init__(self):
		super(TestGrabber, self).__init__()

	def grab(self, *arg):
		return {'name':u'测试','type':'textlines','data':[{'text':u'空空如也'}]}	