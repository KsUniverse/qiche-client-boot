INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634445065064345601', 1123598815738675201, 'activeCode', '激活码', 'menu', '/archives/activeCode', NULL, 1, 1, 0, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634445065064345602', '1634445065064345601', 'activeCode_add', '新增', 'add', '/archives/activeCode/add', 'plus', 1, 2, 1, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634445065064345603', '1634445065064345601', 'activeCode_edit', '修改', 'edit', '/archives/activeCode/edit', 'form', 2, 2, 2, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634445065064345604', '1634445065064345601', 'activeCode_delete', '删除', 'delete', '/api/blade-activeCode/activeCode/remove', 'delete', 3, 2, 3, 1, NULL, 0);
INSERT INTO `blade_menu`(`id`, `parent_id`, `code`, `name`, `alias`, `path`, `source`, `sort`, `category`, `action`, `is_open`, `remark`, `is_deleted`)
VALUES ('1634445065064345605', '1634445065064345601', 'activeCode_view', '查看', 'view', '/archives/activeCode/view', 'file-text', 4, 2, 2, 1, NULL, 0);
