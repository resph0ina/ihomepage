import json

def object2dict(obj):
    #convert object to a dict
    d = {}
    d['__class__'] = obj.__class__.__name__
    d['__module__'] = obj.__module__
    d.update(obj.__dict__)
    return d
 
def dict2object(d):
    #convert dict to object
    if'__class__' in d:
        class_name = d.pop('__class__')
        module_name = d.pop('__module__')
        module = __import__(module_name)
        class_ = getattr(module,class_name)
        args = dict((key.encode('ascii'), value) for key, value in d.items()) #get args
        inst = class_(**args) #create new instance
    else:
        inst = d
    return inst

class position:
    x = 0
    y = 0
    def __init__(self, x, y):
        self.x = x
        self.y = y

class Block:
    uid = 0
    def __init__(self, x, y, style, width = 1, height = 1):
        Block.uid += 1
        self.uid = Block.uid
        self.position = position(x, y)
        self.style = style
        self.width = width
        self.height = height
        self.content = {}

# initial_block = Block(20, 10, 'searchblock')
# initial_blocks = [initial_block]
# json_initial = [json.dumps(initial_block, default = object2dict)]
