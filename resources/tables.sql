-- public.auth_user definition

-- Drop table

-- DROP TABLE public.auth_user;

CREATE TABLE public.auth_user
(
	id int8 NOT NULL, -- 数据ID
	username text NOT NULL, -- 用户名
	password_hash text NOT NULL, -- 密码
	created_time timestamp(3) DEFAULT CURRENT_TIMESTAMP NULL, -- 数据创建时间
	updated_time timestamp(3) DEFAULT CURRENT_TIMESTAMP NULL, -- 数据更新时间
	updated_by_user_id int8 NULL, -- 数据更新人ID
	created_by_user_id int8 NULL, -- 数据创建人ID
    CONSTRAINT auth_user_pkey PRIMARY KEY (id),
    CONSTRAINT auth_user_username_key UNIQUE (username)
);
COMMENT
ON TABLE public.auth_user IS '用户数据表';

-- Column comments

COMMENT
ON COLUMN public.auth_user.id IS '数据ID';
COMMENT
ON COLUMN public.auth_user.username IS '用户名';
COMMENT
ON COLUMN public.auth_user.password_hash IS '密码';
COMMENT
ON COLUMN public.auth_user.created_time IS '数据创建时间';
COMMENT
ON COLUMN public.auth_user.updated_time IS '数据更新时间';
COMMENT
ON COLUMN public.auth_user.updated_by_user_id IS '数据更新人ID';
COMMENT
ON COLUMN public.auth_user.created_by_user_id IS '数据创建人ID';

-- public.auth_page_meta definition

-- Drop table

-- DROP TABLE public.auth_page_meta;

CREATE TABLE public.auth_page_meta (
	id int8 NOT NULL, -- 数据ID
	page_path text NOT NULL, -- 页面路径
	page_rank int2 DEFAULT 5 NOT NULL, -- 排序值
	page_name text NOT NULL, -- 页面英文名
	page_title text NOT NULL, -- 页面标题
	page_icon text NOT NULL, -- 页面图标
	parent_id int8 NULL, -- 上级菜单ID
	created_time timestamp(3) DEFAULT CURRENT_TIMESTAMP NULL, -- 数据创建时间
	updated_time timestamp(3) DEFAULT CURRENT_TIMESTAMP NULL, -- 数据更新时间
	updated_by_user_id int8 NULL, -- 数据更新人ID
	created_by_user_id int8 NULL, -- 数据创建人ID
	CONSTRAINT auth_page_meta_page_name_key UNIQUE (page_name),
	CONSTRAINT auth_page_meta_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE public.auth_page_meta IS '页面路由数据表';

-- Column comments

COMMENT ON COLUMN public.auth_page_meta.id IS '数据ID';
COMMENT ON COLUMN public.auth_page_meta.page_path IS '页面路径';
COMMENT ON COLUMN public.auth_page_meta.page_rank IS '排序值';
COMMENT ON COLUMN public.auth_page_meta.page_name IS '页面英文名';
COMMENT ON COLUMN public.auth_page_meta.page_title IS '页面标题';
COMMENT ON COLUMN public.auth_page_meta.page_icon IS '页面图标';
COMMENT ON COLUMN public.auth_page_meta.parent_id IS '上级菜单ID';
COMMENT ON COLUMN public.auth_page_meta.created_time IS '数据创建时间';
COMMENT ON COLUMN public.auth_page_meta.updated_time IS '数据更新时间';
COMMENT ON COLUMN public.auth_page_meta.updated_by_user_id IS '数据更新人ID';
COMMENT ON COLUMN public.auth_page_meta.created_by_user_id IS '数据创建人ID';

