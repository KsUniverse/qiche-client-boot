INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634444912655921153', 1123598815738675201, 'downloadRecord', '文件下载记录', 'menu', '/archives/downloadRecord', NULL, 1, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634444912655921154', '1634444912655921153', 'downloadRecord_add', '新增', 'add', '/archives/downloadRecord/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634444912655921155', '1634444912655921153', 'downloadRecord_edit', '修改', 'edit', '/archives/downloadRecord/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634444912655921156', '1634444912655921153', 'downloadRecord_delete', '删除', 'delete', '/api/blade-downloadRecord/downloadRecord/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634444912655921157', '1634444912655921153', 'downloadRecord_view', '查看', 'view', '/archives/downloadRecord/view', 'file-text', 4, 2, 2, 1, NULL, 0);
