import json, block

b=block.Block('textlines')
s=json.dumps([b], default = block.object2dict)
d=json.loads(s)
a=block.dict2object(d[0])
print a