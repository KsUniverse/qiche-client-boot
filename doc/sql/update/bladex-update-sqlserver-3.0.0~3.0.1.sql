-- -----------------------------------
-- 修改 代码生成表字段类型
-- -----------------------------------
EXEC sp_rename '[dbo].[blade_code].[datasource_id]', 'model_id', 'COLUMN'
GO

IF ((SELECT COUNT(*) FROM ::fn_listextendedproperty('MS_Description',
'SCHEMA', N'dbo',
'TABLE', N'blade_code',
'COLUMN', N'model_id')) > 0)
  EXEC sp_updateextendedproperty
'MS_Description', N'数据模型主键',
'SCHEMA', N'dbo',
'TABLE', N'blade_code',
'COLUMN', N'model_id'
ELSE
  EXEC sp_addextendedproperty
'MS_Description', N'数据模型主键',
'SCHEMA', N'dbo',
'TABLE', N'blade_code',
'COLUMN', N'model_id';

-- -----------------------------------
-- 新增 代码生成表字段类型
-- -----------------------------------
ALTER TABLE [dbo].[blade_code] ADD [template_type] nvarchar(32)
    GO

ALTER TABLE [dbo].[blade_code] ADD [author] nvarchar(32)
    GO

ALTER TABLE [dbo].[blade_code] ADD [sub_model_id] nvarchar(32)
    GO

ALTER TABLE [dbo].[blade_code] ADD [sub_fk_id] nvarchar(32)
    GO

ALTER TABLE [dbo].[blade_code] ADD [tree_id] nvarchar(32)
    GO

ALTER TABLE [dbo].[blade_code] ADD [tree_pid] nvarchar(32)
    GO

ALTER TABLE [dbo].[blade_code] ADD [tree_name] nvarchar(64)
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'模版类型',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_code',
    'COLUMN', N'template_type'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'作者信息',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_code',
    'COLUMN', N'author'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'子表模型主键',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_code',
    'COLUMN', N'sub_model_id'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'子表绑定外键',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_code',
    'COLUMN', N'sub_fk_id'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'树主键字段',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_code',
    'COLUMN', N'tree_id'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'树父主键字段',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_code',
    'COLUMN', N'tree_pid'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'树名称字段',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_code',
    'COLUMN', N'tree_name';

-- -----------------------------------
-- 新增模型设计菜单
-- -----------------------------------
INSERT INTO [dbo].[blade_menu]([id], [parent_id], [code], [name], [alias], [path], [source], [sort], [category], [action], [is_open], [component], [remark], [is_deleted]) VALUES (1161272593873321996, 1123598815738675217, N'model', N'数据模型设计', N'menu', N'/tool/model', N'iconfont icon-biaodan', 3, 1, 0, 1, N'', NULL, 0);

-- -----------------------------------
-- 新增 数据模型表
-- -----------------------------------
CREATE TABLE [dbo].[blade_model] (
    [id] bigint NOT NULL,
    [datasource_id] bigint NULL,
    [model_name] nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [model_code] nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [model_table] nvarchar(100) COLLATE Chinese_PRC_CI_AS NULL,
    [model_class] nvarchar(100) COLLATE Chinese_PRC_CI_AS NULL,
    [model_remark] nvarchar(500) COLLATE Chinese_PRC_CI_AS NULL,
    [create_user] bigint NULL,
    [create_dept] bigint NULL,
    [create_time] datetime2(0) NULL,
    [update_user] bigint NULL,
    [update_time] datetime2(0) NULL,
    [status] int NULL,
    [is_deleted] int NULL,
    PRIMARY KEY CLUSTERED ([id])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    )
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'主键',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'id'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'数据源主键',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'datasource_id'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'模型名称',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'model_name'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'模型编号',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'model_code'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'物理表名',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'model_table'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'模型类名',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'model_class'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'模型备注',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'model_remark'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'创建人',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'create_user'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'创建部门',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'create_dept'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'创建时间',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'create_time'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'修改人',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'update_user'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'修改时间',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'update_time'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'状态',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'status'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'是否已删除',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model',
    'COLUMN', N'is_deleted'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'数据模型表',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model';

-- -----------------------------------
-- 新增 数据原型表
-- -----------------------------------
CREATE TABLE [dbo].[blade_model_prototype] (
    [id] bigint NOT NULL,
    [model_id] bigint NULL,
    [jdbc_name] nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [jdbc_type] nvarchar(20) COLLATE Chinese_PRC_CI_AS NULL,
    [comment] nvarchar(500) COLLATE Chinese_PRC_CI_AS NULL,
    [property_type] nvarchar(20) COLLATE Chinese_PRC_CI_AS NULL,
    [property_entity] nvarchar(500) COLLATE Chinese_PRC_CI_AS NULL,
    [property_name] nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [is_list] int NULL,
    [is_form] int NULL,
    [is_row] int NULL,
    [component_type] nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [dict_code] nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [is_required] int NULL,
    [validate_type] nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [is_query] int NULL,
    [query_type] nvarchar(50) COLLATE Chinese_PRC_CI_AS NULL,
    [create_user] bigint NULL,
    [create_dept] bigint NULL,
    [create_time] datetime2(0) NULL,
    [update_user] bigint NULL,
    [update_time] datetime2(0) NULL,
    [status] int NULL,
    [is_deleted] int NULL,
    PRIMARY KEY CLUSTERED ([id])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    )
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'主键',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'id'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'模型主键',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'model_id'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'物理列名',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'jdbc_name'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'物理类型',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'jdbc_type'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'注释说明',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'comment'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'实体类型',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'property_type'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'实体类型引用',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'property_entity'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'实体列名',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'property_name'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'列表显示',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'is_list'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'表单显示',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'is_form'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'独占一行',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'is_row'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'组件类型',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'component_type'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'字典编码',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'dict_code'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'是否必填',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'is_required'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'验证类型',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'validate_type'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'查询配置',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'is_query'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'查询类型',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'query_type'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'创建人',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'create_user'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'创建部门',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'create_dept'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'创建时间',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'create_time'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'修改人',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'update_user'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'修改时间',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'update_time'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'状态',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'status'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'是否已删除',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype',
    'COLUMN', N'is_deleted'
    GO

    EXEC sp_addextendedproperty
    'MS_Description', N'数据原型表',
    'SCHEMA', N'dbo',
    'TABLE', N'blade_model_prototype';
