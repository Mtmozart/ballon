CREATE TYPE user_type AS ENUM ('ADMIN', 'CONSUMER');

CREATE TABLE admins (
    id UUID PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    type_user user_type NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE consumers (
    id UUID PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    type_user user_type NOT NULL DEFAULT 'CONSUMER',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE expenses (
    id UUID PRIMARY KEY,
    month VARCHAR(20) NOT NULL CHECK (month IN ('JANUARY', 'FEBRUARY', 'MARCH', 'APRIL', 'MAY', 'JUNE', 'JULY', 'AUGUST', 'SEPTEMBER', 'OCTOBER', 'NOVEMBER', 'DECEMBER')),
    year INT NOT NULL CHECK (year >= 2000 AND year <= 2100),
    title VARCHAR(50) NOT NULL,
    value DECIMAL(10,2) NOT NULL CHECK (value >= 0),
    consumer_id UUID NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY (consumer_id) REFERENCES consumers(id) ON DELETE CASCADE
);

CREATE TABLE categories (
    id BIGINT PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL UNIQUE CHECK (title IN ('ESSENTIALS', 'LEISURES', 'DISCRETIONARY', 'INVESTMENTS'))
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
    (1, 'ESSENTIALS'),
    (2, 'LEISURES'),
    (3, 'DISCRETIONARY'),
    (4, 'INVESTMENTS');
