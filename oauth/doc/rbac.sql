CREATE TABLE `tb_permission`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT,
    `parent_id`   bigint(20)   DEFAULT NULL COMMENT '父权限',
    `name`        varchar(64)  NOT NULL COMMENT '权限名称',
    `enname`      varchar(64)  NOT NULL COMMENT '权限英文名称',
    `url`         varchar(255) NOT NULL COMMENT '授权路径',
    `description` varchar(200) DEFAULT NULL COMMENT '备注',
    `create_time` datetime     NOT NULL,
    `update_time` datetime     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 44
  DEFAULT CHARSET = utf8 COMMENT ='权限表';
INSERT INTO tb_permission (id, parent_id, name, enname, url, description, create_time, update_time)
VALUES (37, 0, '系统管理', 'System', '/', null, '2019-04-04 23:22:54', '2019-04-04 23:22:56');
INSERT INTO tb_permission (id, parent_id, name, enname, url, description, create_time, update_time)
VALUES (38, 37, '用户管理', 'SystemUser', '/users/', null, '2019-04-04 23:25:31', '2019-04-04 23:25:33');
INSERT INTO tb_permission (id, parent_id, name, enname, url, description, create_time, update_time)
VALUES (39, 38, '查看用户', 'SystemMemberView', '', null, '2019-04-04 15:30:30', '2019-04-04 15:30:43');
INSERT INTO tb_permission (id, parent_id, name, enname, url, description, create_time, update_time)
VALUES (40, 38, '查看订单', 'SystemOrderView', '', null, '2019-04-04 15:30:31', '2019-04-04 15:30:44');
INSERT INTO tb_permission (id, parent_id, name, enname, url, description, create_time, update_time)
VALUES (41, 38, '删除订单', 'SystemOrderDelete', '', null, '2019-04-04 15:30:32', '2019-04-04 15:30:45');
INSERT INTO tb_permission (id, parent_id, name, enname, url, description, create_time, update_time)
VALUES (42, 38, '删除用户', 'SystemMemberDelete', '', null, '2019-04-04 15:30:48', '2019-04-04 15:30:45');

CREATE TABLE `tb_role`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `parent_id`   bigint(20)   DEFAULT NULL COMMENT '父角色',
    `name`        varchar(64) NOT NULL COMMENT '角色名称',
    `enname`      varchar(64) NOT NULL COMMENT '角色英文名称',
    `description` varchar(200) DEFAULT NULL COMMENT '备注',
    `create_time` datetime    NOT NULL,
    `update_time` datetime    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 38
  DEFAULT CHARSET = utf8 COMMENT ='角色表';
insert into `tb_role`(`id`, `parent_id`, `name`, `enname`, `description`, `create_time`, `update_time`)
values (37, 0, '超级管理员', 'admin', NULL, '2019-04-04 23:22:03', '2019-04-04 23:22:05');
INSERT INTO tb_role (id, parent_id, name, enname, description, create_time, update_time)
VALUES (38, 0, '普通用户', 'user', null, '2020-11-22 19:05:54', '2020-11-22 19:05:56');

CREATE TABLE `tb_role_permission`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `role_id`       bigint(20) NOT NULL COMMENT '角色 ID',
    `permission_id` bigint(20) NOT NULL COMMENT '权限 ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 43
  DEFAULT CHARSET = utf8 COMMENT ='角色权限表';
insert into `tb_role_permission`(`id`, `role_id`, `permission_id`)
values (37, 37, 37),
       (38, 37, 38),
       (39, 37, 39),
       (40, 37, 40),
       (41, 37, 41),
       (42, 37, 42);
INSERT INTO tb_role_permission (id, role_id, permission_id)
VALUES (43, 38, 39);

CREATE TABLE `tb_user`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `username`    varchar(50) NOT NULL COMMENT '用户名',
    `password`    varchar(64) NOT NULL COMMENT '密码，加密存储',
    `phone`       varchar(20) DEFAULT NULL COMMENT '注册手机号',
    `email`       varchar(50) DEFAULT NULL COMMENT '注册邮箱',
    `create_time` datetime    NOT NULL,
    `update_time` datetime    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`) USING BTREE,
    UNIQUE KEY `phone` (`phone`) USING BTREE,
    UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 38
  DEFAULT CHARSET = utf8 COMMENT ='用户表';
insert into `tb_user`(`id`, `username`, `password`, `phone`, `email`, `create_time`, `update_time`)
values (37, 'admin', '$2a$10$9ZhDOBp.sRKat4l14ygu/.LscxrMUcDAfeVOEPiYwbcRkoB09gCmi', '18888888888', 'tallon@qq.com',
        '2020-11-20 23:21:27', '2020-11-20 23:21:29');
INSERT INTO tb_user (id, username, password, phone, email, create_time, update_time)
VALUES (38, 'eric', '$2a$10$VeVNo8ap3q5FwshToktfeOxIQWl.xBUNaLTDAI6FAJF9rTVJ5CWfm', '16711111111', 'user@qq.com',
        '2020-11-22 19:04:47', '2020-11-22 19:04:50');

CREATE TABLE `tb_user_role`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) NOT NULL COMMENT '用户 ID',
    `role_id` bigint(20) NOT NULL COMMENT '角色 ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 38
  DEFAULT CHARSET = utf8 COMMENT ='用户角色表';
insert into `tb_user_role`(`id`, `user_id`, `role_id`)
values (37, 37, 37);
INSERT INTO tb_user_role (id, user_id, role_id)
VALUES (38, 38, 38);