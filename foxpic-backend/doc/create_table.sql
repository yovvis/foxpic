# 数据库初始化
-- 创建库
create database if not exists demo;
-- 切换库
use demo;
-- 用户表
create table f_user
(
    id            bigint auto_increment comment 'id' primary key,
    userAccount   varchar(256)                           not null comment '账号',
    userPassword  varchar(512)                           not null comment '密码',
    unionId       varchar(256) null comment '微信开放平台id',
    mpOpenId      varchar(256) null comment '公众号openId',
    userName      varchar(256) null comment '用户昵称',
    userAvatar    varchar(1024) null comment '用户头像',
    userProfile   varchar(512) null comment '用户简介',
    userRole      varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    vipNumber     bigint null comment '会员编号',
    vipCode       varchar(128) null comment '会员兑换码',
    vipExpireTime datetime null comment '会员过期时间',
    shareCode     varchar(20) null comment '分享码',
    inviteUser    bigint null comment '邀请用户id',
    editTime      datetime     default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime    datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime    datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete      tinyint      default 0                 not null comment '是否删除'
) comment '用户' collate = utf8mb4_unicode_ci;
create index idx_userName on f_user (userName);
create unique index uk_userAccount on f_user (userAccount)

-- 图片表
create table if not exists picture
(
    id           bigint auto_increment comment 'id' primary key,
    url          varchar(512)                       not null comment '图片 url',
    name         varchar(128)                       not null comment '图片名称',
    introduction varchar(512)                       null comment '简介',
    category     varchar(64)                        null comment '分类',
    tags         varchar(512)                       null comment '标签（JSON 数组）',
    picSize      bigint                             null comment '图片体积',
    picWidth     int                                null comment '图片宽度',
    picHeight    int                                null comment '图片高度',
    picScale     double                             null comment '图片宽高比例',
    picFormat    varchar(32)                        null comment '图片格式',
    userId       bigint                             not null comment '创建用户 id',
    createTime   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    editTime     datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    updateTime   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除',
    INDEX idx_name (name),                 -- 提升基于图片名称的查询性能
    INDEX idx_introduction (introduction), -- 用于模糊搜索图片简介
    INDEX idx_category (category),         -- 提升基于分类的查询性能
    INDEX idx_tags (tags),                 -- 提升基于标签的查询性能
    INDEX idx_userId (userId)              -- 提升基于用户 ID 的查询性能
    ) comment '图片' collate = utf8mb4_unicode_ci;

Alter table picture
    add column reviewStatus int default 0 not null comment '审核状态：0-待审核；1-通过；2-拒绝',
    add column reviewMessage varchar(512) null comment '审核信息',
    add column reviewerId bigint null comment '审核人ID',
    add column revieweTime datetime null comment '审核时间'

Alter table picture
    add column thumbnailUrl varchar(512) null comment '缩略图Url'

-- 空间表
create table if not exists space
(
    id         bigint auto_increment comment 'id' primary key,
    spaceName  varchar(128)                       null comment '空间名称',
    spaceLevel int      default 0                 null comment '空间级别：0-普通版 1-专业版 2-旗舰版',
    maxSize    bigint   default 0                 null comment '空间图片的最大总大小',
    maxCount   bigint   default 0                 null comment '空间图片的最大数量',
    totalSize  bigint   default 0                 null comment '当前空间下图片的总大小',
    totalCount bigint   default 0                 null comment '当前空间下的图片数量',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    editTime   datetime default CURRENT_TIMESTAMP not null comment '编辑时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    -- 索引设计
    index idx_userId (userId),        -- 提升基于用户的查询效率
    index idx_spaceName (spaceName),  -- 提升基于空间名称的查询效率
    index idx_spaceLevel (spaceLevel) -- 提升按空间级别查询的效率
    ) comment '空间' collate = utf8mb4_unicode_ci;

-- 添加新列
ALTER TABLE picture
    ADD COLUMN spaceId bigint  null comment '空间 id（为空表示公共空间）';

-- 创建索引
CREATE INDEX idx_spaceId ON picture (spaceId);


-- 用户数据(密码 11111)
INSERT INTO f_user(id, userAccount, userPassword, unionId, mpOpenId, userName, userAvatar, userProfile, userRole)
VALUES (1, 'user1', '492a65bef0ab2fac75758f004f3eaf35', 'unionId1', 'mpOpenId1', 'user1',
        'https://api.oss.tenyon.cn/tenyon/assets/default.png', '喜欢编程的小白', 'user'),
       (2, 'user2', '492a65bef0ab2fac75758f004f3eaf35', 'unionId2', 'mpOpenId2', 'user2',
        'https://api.oss.tenyon.cn/tenyon/assets/default.png', '全栈开发工程师', 'user'),
       (3, 'user3', '492a65bef0ab2fac75758f004f3eaf35', 'unionId3', 'mpOpenId3', 'user3',
        'https://api.oss.tenyon.cn/tenyon/assets/default.png', '前端爱好者', 'user'),
       (4, 'user4', '492a65bef0ab2fac75758f004f3eaf35', 'unionId4', 'mpOpenId4', 'user4',
        'https://api.oss.tenyon.cn/tenyon/assets/default.png', '后端开发工程师', 'user'),
       (5, 'admin', '492a65bef0ab2fac75758f004f3eaf35', NULL, NULL, 'admin123',
        'https://api.oss.tenyon.cn/tenyon/assets/default.png', '系统管理员', 'admin');