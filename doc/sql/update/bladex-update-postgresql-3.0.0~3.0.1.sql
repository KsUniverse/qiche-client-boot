-- -----------------------------------
-- 给代码生成添加字段
-- -----------------------------------
ALTER TABLE "blade_code" RENAME COLUMN "datasource_id" TO "model_id";
COMMENT ON COLUMN "blade_code"."model_id" IS '数据模型主键';

-- -----------------------------------
-- 新增 代码生成表字段类型
-- -----------------------------------
ALTER TABLE "blade_code"
    ADD COLUMN "template_type" varchar(32),
    ADD COLUMN "author" varchar(32),
    ADD COLUMN "sub_model_id" varchar(32),
    ADD COLUMN "sub_fk_id" varchar(32),
    ADD COLUMN "tree_id" varchar(32),
    ADD COLUMN "tree_pid" varchar(32),
    ADD COLUMN "tree_name" varchar(64);

COMMENT ON COLUMN "blade_code"."template_type" IS '模版类型';

COMMENT ON COLUMN "blade_code"."author" IS '作者信息';

COMMENT ON COLUMN "blade_code"."sub_model_id" IS '子表模型主键';

COMMENT ON COLUMN "blade_code"."sub_fk_id" IS '子表绑定外键';

COMMENT ON COLUMN "blade_code"."tree_id" IS '树主键字段';

COMMENT ON COLUMN "blade_code"."tree_pid" IS '树父主键字段';

COMMENT ON COLUMN "blade_code"."tree_name" IS '树名称字段';


-- -----------------------------------
-- 新增模型设计菜单
-- -----------------------------------
INSERT INTO "blade_menu"("id", "parent_id", "code", "name", "alias", "path", "source", "sort", "category", "action", "is_open", "component", "remark", "is_deleted") VALUES (1161272593873321996, 1123598815738675217, 'model', '数据模型设计', 'menu', '/tool/model', 'iconfont icon-biaodan', 3, 1, 0, 1, '', '', 0);

-- -----------------------------------
-- 新增 数据模型表
-- -----------------------------------
CREATE TABLE "blade_model" (
    "id" int8 NOT NULL,
    "datasource_id" int8,
    "model_name" varchar(50) COLLATE "pg_catalog"."default",
    "model_code" varchar(50) COLLATE "pg_catalog"."default",
    "model_table" varchar(100) COLLATE "pg_catalog"."default",
    "model_class" varchar(100) COLLATE "pg_catalog"."default",
    "model_remark" varchar(500) COLLATE "pg_catalog"."default",
    "create_user" int8,
    "create_dept" int8,
    "create_time" timestamp(6),
    "update_user" int8,
    "update_time" timestamp(6),
    "status" int4,
    "is_deleted" int4,
    PRIMARY KEY ("id")
)
;

COMMENT ON COLUMN "blade_model"."id" IS '主键';

COMMENT ON COLUMN "blade_model"."datasource_id" IS '数据源主键';

COMMENT ON COLUMN "blade_model"."model_name" IS '模型名称';

COMMENT ON COLUMN "blade_model"."model_code" IS '模型编号';

COMMENT ON COLUMN "blade_model"."model_table" IS '物理表名';

COMMENT ON COLUMN "blade_model"."model_class" IS '模型类名';

COMMENT ON COLUMN "blade_model"."model_remark" IS '模型备注';

COMMENT ON COLUMN "blade_model"."create_user" IS '创建人';

COMMENT ON COLUMN "blade_model"."create_dept" IS '创建部门';

COMMENT ON COLUMN "blade_model"."create_time" IS '创建时间';

COMMENT ON COLUMN "blade_model"."update_user" IS '修改人';

COMMENT ON COLUMN "blade_model"."update_time" IS '修改时间';

COMMENT ON COLUMN "blade_model"."status" IS '状态';

COMMENT ON COLUMN "blade_model"."is_deleted" IS '是否已删除';

COMMENT ON TABLE "blade_model" IS '数据模型表';

-- -----------------------------------
-- 新增 数据原型表
-- -----------------------------------
CREATE TABLE "blade_model_prototype" (
    "id" int8 NOT NULL,
    "model_id" int8,
    "jdbc_name" varchar(50) COLLATE "pg_catalog"."default",
    "jdbc_type" varchar(20) COLLATE "pg_catalog"."default",
    "comment" varchar(500) COLLATE "pg_catalog"."default",
    "property_type" varchar(20) COLLATE "pg_catalog"."default",
    "property_entity" varchar(500) COLLATE "pg_catalog"."default",
    "property_name" varchar(50) COLLATE "pg_catalog"."default",
    "is_list" int4,
    "is_form" int4,
    "is_row" int4,
    "component_type" varchar(50) COLLATE "pg_catalog"."default",
    "dict_code" varchar(50) COLLATE "pg_catalog"."default",
    "is_required" int4,
    "validate_type" varchar(50) COLLATE "pg_catalog"."default",
    "is_query" int4,
    "query_type" varchar(50) COLLATE "pg_catalog"."default",
    "create_user" int8,
    "create_dept" int8,
    "create_time" timestamp(6),
    "update_user" int8,
    "update_time" timestamp(6),
    "status" int4,
    "is_deleted" int4,
    PRIMARY KEY ("id")
)
;

COMMENT ON COLUMN "blade_model_prototype"."id" IS '主键';

COMMENT ON COLUMN "blade_model_prototype"."model_id" IS '模型主键';

COMMENT ON COLUMN "blade_model_prototype"."jdbc_name" IS '物理列名';

COMMENT ON COLUMN "blade_model_prototype"."jdbc_type" IS '物理类型';

COMMENT ON COLUMN "blade_model_prototype"."comment" IS '注释说明';

COMMENT ON COLUMN "blade_model_prototype"."property_type" IS '实体类型';

COMMENT ON COLUMN "blade_model_prototype"."property_entity" IS '实体类型引用';

COMMENT ON COLUMN "blade_model_prototype"."property_name" IS '实体列名';

COMMENT ON COLUMN "blade_model_prototype"."is_list" IS '列表显示';

COMMENT ON COLUMN "blade_model_prototype"."is_form" IS '表单显示';

COMMENT ON COLUMN "blade_model_prototype"."is_row" IS '独占一行';

COMMENT ON COLUMN "blade_model_prototype"."component_type" IS '组件类型';

COMMENT ON COLUMN "blade_model_prototype"."dict_code" IS '字典编码';

COMMENT ON COLUMN "blade_model_prototype"."is_required" IS '是否必填';

COMMENT ON COLUMN "blade_model_prototype"."validate_type" IS '验证类型';

COMMENT ON COLUMN "blade_model_prototype"."is_query" IS '查询配置';

COMMENT ON COLUMN "blade_model_prototype"."query_type" IS '查询类型';

COMMENT ON COLUMN "blade_model_prototype"."create_user" IS '创建人';

COMMENT ON COLUMN "blade_model_prototype"."create_dept" IS '创建部门';

COMMENT ON COLUMN "blade_model_prototype"."create_time" IS '创建时间';

COMMENT ON COLUMN "blade_model_prototype"."update_user" IS '修改人';

COMMENT ON COLUMN "blade_model_prototype"."update_time" IS '修改时间';

COMMENT ON COLUMN "blade_model_prototype"."status" IS '状态';

COMMENT ON COLUMN "blade_model_prototype"."is_deleted" IS '是否已删除';

COMMENT ON TABLE "blade_model_prototype" IS '数据原型表';
