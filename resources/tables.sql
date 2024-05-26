-- public.auth_role definition

-- Drop table

-- DROP TABLE public.auth_role;

CREATE TABLE public.auth_role (
	id int8 NOT NULL, -- 数据ID
	username text NOT NULL, -- 用户名
	password_hash text NOT NULL, -- 密码
	created_time timestamp(3) DEFAULT CURRENT_TIMESTAMP NULL, -- 数据创建时间
	updated_time timestamp(3) DEFAULT CURRENT_TIMESTAMP NULL, -- 数据更新时间
	updated_by_user_id int8 NULL, -- 数据更新人ID
	created_by_user_id int8 NULL, -- 数据创建人ID
	CONSTRAINT auth_role_pkey PRIMARY KEY (id),
	CONSTRAINT auth_role_username_key UNIQUE (username)
);
COMMENT ON TABLE public.auth_role IS '用户数据表';

-- Column comments

COMMENT ON COLUMN public.auth_role.id IS '数据ID';
COMMENT ON COLUMN public.auth_role.username IS '用户名';
COMMENT ON COLUMN public.auth_role.password_hash IS '密码';
COMMENT ON COLUMN public.auth_role.created_time IS '数据创建时间';
COMMENT ON COLUMN public.auth_role.updated_time IS '数据更新时间';
COMMENT ON COLUMN public.auth_role.updated_by_user_id IS '数据更新人ID';
COMMENT ON COLUMN public.auth_role.created_by_user_id IS '数据创建人ID';
