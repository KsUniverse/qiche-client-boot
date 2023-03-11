INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634444915419967489', 1123598815738675201, 'fileDirectory', '文件目录', 'menu', '/archives/fileDirectory', NULL, 1, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634444915419967490', '1634444915419967489', 'fileDirectory_add', '新增', 'add', '/archives/fileDirectory/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634444915419967491', '1634444915419967489', 'fileDirectory_edit', '修改', 'edit', '/archives/fileDirectory/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634444915419967492', '1634444915419967489', 'fileDirectory_delete', '删除', 'delete', '/api/blade-fileDirectory/fileDirectory/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634444915419967493', '1634444915419967489', 'fileDirectory_view', '查看', 'view', '/archives/fileDirectory/view', 'file-text', 4, 2, 2, 1, NULL, 0);
