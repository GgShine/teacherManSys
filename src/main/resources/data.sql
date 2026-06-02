INSERT INTO t_user (
    username,
    password,
    real_name,
    email,
    phone,
    department_id,
    role_id,
    status,
    create_time,
    update_time,
    deleted
)
SELECT
    'admin',
    '123456',
    '系统管理员',
    'admin@example.com',
    '13800000000',
    1,
    4,
    1,
    NOW(),
    NOW(),
    0
WHERE NOT EXISTS (
    SELECT 1 FROM t_user WHERE username = 'admin' AND deleted = 0
);

INSERT INTO t_department (
    name,
    parent_id,
    level,
    description,
    create_time,
    update_time,
    deleted
)
SELECT
    '默认学校',
    0,
    1,
    '系统初始化部门',
    NOW(),
    NOW(),
    0
WHERE NOT EXISTS (
    SELECT 1 FROM t_department WHERE name = '默认学校' AND deleted = 0
);
