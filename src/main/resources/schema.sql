CREATE TABLE IF NOT EXISTS t_user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(128) NOT NULL,
    real_name VARCHAR(64),
    email VARCHAR(128),
    phone VARCHAR(32),
    department_id INT,
    role_id INT NOT NULL DEFAULT 1,
    status TINYINT NOT NULL DEFAULT 1,
    create_time DATETIME,
    update_time DATETIME,
    deleted TINYINT NOT NULL DEFAULT 0,
    UNIQUE KEY uk_user_username (username)
);

CREATE TABLE IF NOT EXISTS t_department (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    parent_id INT NOT NULL DEFAULT 0,
    level INT NOT NULL,
    description VARCHAR(255),
    create_time DATETIME,
    update_time DATETIME,
    deleted TINYINT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS t_archive_template (
    id INT PRIMARY KEY AUTO_INCREMENT,
    template_name VARCHAR(128) NOT NULL,
    template_code VARCHAR(64) NOT NULL,
    description VARCHAR(255),
    fields TEXT,
    attachment_rules TEXT,
    status TINYINT NOT NULL DEFAULT 1,
    department_id INT,
    creator_id INT,
    create_time DATETIME,
    update_time DATETIME,
    deleted TINYINT NOT NULL DEFAULT 0,
    UNIQUE KEY uk_template_code (template_code)
);

CREATE TABLE IF NOT EXISTS t_archive_record (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    template_id INT,
    record_title VARCHAR(255) NOT NULL,
    content_data LONGTEXT,
    file_paths LONGTEXT,
    submit_status TINYINT NOT NULL DEFAULT 0,
    current_level INT,
    next_approver_id INT,
    review_comments VARCHAR(1000),
    package_path VARCHAR(255),
    is_packed TINYINT NOT NULL DEFAULT 0,
    submit_time DATETIME,
    review_time DATETIME,
    archive_time DATETIME,
    create_time DATETIME,
    update_time DATETIME,
    deleted TINYINT NOT NULL DEFAULT 0,
    KEY idx_record_user_id (user_id),
    KEY idx_record_status (submit_status)
);

CREATE TABLE IF NOT EXISTS t_review_record (
    id INT PRIMARY KEY AUTO_INCREMENT,
    record_id INT NOT NULL,
    reviewer_id INT NOT NULL,
    review_level INT NOT NULL,
    review_result TINYINT NOT NULL,
    comments VARCHAR(1000),
    review_time DATETIME,
    file_path VARCHAR(255),
    is_archived TINYINT NOT NULL DEFAULT 0,
    create_time DATETIME,
    deleted TINYINT NOT NULL DEFAULT 0,
    KEY idx_review_record_id (record_id)
);
