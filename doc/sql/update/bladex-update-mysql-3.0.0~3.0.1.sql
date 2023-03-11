-- -----------------------------------
-- 修改 代码生成表字段类型
-- -----------------------------------
ALTER TABLE `blade_code`
    CHANGE COLUMN `datasource_id` `model_id` bigint(20) NULL DEFAULT NULL COMMENT '数据模型主键' AFTER `id`;

-- -----------------------------------
-- 新增 代码生成表字段类型
-- -----------------------------------
ALTER TABLE `blade_code`
    ADD COLUMN `template_type` varchar(32) NULL COMMENT '模版类型' AFTER `package_name`,
    ADD COLUMN `author` varchar(32) NULL COMMENT '作者信息' AFTER `template_type`,
    ADD COLUMN `sub_model_id` varchar(32) NULL COMMENT '子表模型主键' AFTER `author`,
    ADD COLUMN `sub_fk_id` varchar(32) NULL COMMENT '子表绑定外键' AFTER `sub_model_id`,
    ADD COLUMN `tree_id` varchar(32) NULL COMMENT '树主键字段' AFTER `sub_fk_id`,
    ADD COLUMN `tree_pid` varchar(32) NULL COMMENT '树父主键字段' AFTER `tree_id`,
    ADD COLUMN `tree_name` varchar(64) NULL COMMENT '树名称字段' AFTER `tree_pid`;

-- -----------------------------------
-- 新增模型设计菜单
-- -----------------------------------
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `component`, `remark`, `is_deleted`) VALUES (1161272593873321996, 1123598815738675217, 'model', '数据模型设计', 'menu', '/tool/model', 'iconfont icon-biaodan', 3, 1, 0, 1, '', '', 0);

-- -----------------------------------
-- 新增 数据模型表
-- -----------------------------------
CREATE TABLE `blade_model`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `datasource_id` bigint(20) NULL DEFAULT NULL COMMENT '数据源主键',
  `model_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模型名称',
  `model_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模型编号',
  `model_table` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物理表名',
  `model_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模型类名',
  `model_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模型备注',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) COMMENT = '数据模型表';

-- -----------------------------------
-- 新增 数据原型表
-- -----------------------------------
CREATE TABLE `blade_model_prototype`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_id` bigint(20) NULL DEFAULT NULL COMMENT '模型主键',
  `jdbc_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物理列名',
  `jdbc_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物理类型',
  `comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注释说明',
  `property_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实体类型',
  `property_entity` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实体类型引用',
  `property_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实体列名',
  `is_list` int(2) NULL DEFAULT NULL COMMENT '列表显示',
  `is_form` int(2) NULL DEFAULT NULL COMMENT '表单显示',
  `is_row` int(2) NULL DEFAULT NULL COMMENT '独占一行',
  `component_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件类型',
  `dict_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `is_required` int(2) NULL DEFAULT NULL COMMENT '是否必填',
  `is_query` int(2) NULL DEFAULT NULL COMMENT '查询配置',
  `query_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '查询类型',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) COMMENT = '数据原型表';
