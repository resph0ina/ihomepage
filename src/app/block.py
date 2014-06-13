import json

# class position:
#     x = 0
#     y = 0
#     def __init__(self, x, y):
#         self.x = x
#         self.y = y

class Block:
    uid = 0
    def __init__(self, style, name = '', color = '', x = 0, y = 0, width = 1, height = 1, uid = -1, content = {}, config = {}):
        Block.uid += 1
        if uid == -1:
            self.uid = Block.uid
        else:
            self.uid = uid
        # self.position = position(x, y)
        self.x = x; self.y = y
        self.style = style
        self.name = name
        self.width = width
        self.height = height
        self.content = content
        self.config = config
        self.color = color

# initial_block = Block(20, 10, 'searchblock')
# initial_blocks = [initial_block]
# json_initial = [json.dumps(initial_block, default = object2dict)]
