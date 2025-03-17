CREATE TYPE user_type AS ENUM ('ADMIN', 'CONSUMER');

CREATE TABLE admins (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    type_user user_type NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE consumers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    type_user user_type NOT NULL DEFAULT 'CONSUMER',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE expenses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    month VARCHAR(20) NOT NULL CHECK (month IN ('january', 'february', 'march', 'april', 'may', 'june', 'july', 'august', 'september', 'october', 'november', 'december')),
    year INT NOT NULL CHECK (year >= 2000 AND year <= 2100),
    title VARCHAR(50) NOT NULL,
    value DECIMAL(10,2) NOT NULL CHECK (value >= 0),
    consumer_id UUID NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY (consumer_id) REFERENCES consumers(id) ON DELETE CASCADE
);

CREATE TABLE categories (
    id BIGINT PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL UNIQUE CHECK (title IN ('essentials', 'leisures', 'discretionary', 'investments'))
);

CREATE TABLE expense_categories (
    expense_id UUID NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (expense_id, category_id),
    FOREIGN KEY (expense_id) REFERENCES expenses(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

CREATE TABLE subcategories (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category_id BIGINT NOT NULL,
    consumer_id UUID NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
    FOREIGN KEY (consumer_id) REFERENCES consumers(id) ON DELETE CASCADE
);

INSERT INTO categories (id, title)
VALUES
    (1, 'essentials'),
    (2, 'leisures'),
    (3, 'discretionary'),
    (4, 'investments');
