from sqlalchemy import *
from migrate import *


from migrate.changeset import schema
pre_meta = MetaData()
post_meta = MetaData()
user = Table('user', post_meta,
    Column('id', Integer, primary_key=True, nullable=False),
    Column('username', String(length=80)),
    Column('password', String(length=120)),
    Column('email', String(length=80)),
    Column('homepage', String(length=1000)),
    Column('image', String(length=1000)),
)


def upgrade(migrate_engine):
    # Upgrade operations go here. Don't create your own engine; bind
    # migrate_engine to your metadata
    pre_meta.bind = migrate_engine
    post_meta.bind = migrate_engine
    post_meta.tables['user'].columns['email'].create()
    post_meta.tables['user'].columns['homepage'].create()
    post_meta.tables['user'].columns['image'].create()


def downgrade(migrate_engine):
    # Operations to reverse the above upgrade go here.
    pre_meta.bind = migrate_engine
    post_meta.bind = migrate_engine
    post_meta.tables['user'].columns['email'].drop()
    post_meta.tables['user'].columns['homepage'].drop()
    post_meta.tables['user'].columns['image'].drop()
