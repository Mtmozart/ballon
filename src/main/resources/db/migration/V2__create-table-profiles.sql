CREATE TABLE profiles (
    id BIGINT PRIMARY KEY,
    type VARCHAR(100) NOT NULL
);

CREATE TABLE users_profiles (
    user_id UUID NOT NULL,
    profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, profile_id),
    FOREIGN KEY (profile_id) REFERENCES profiles(id)
);

INSERT INTO profiles VALUES(1, 'ADMIN');
INSERT INTO profiles VALUES(2, 'CONSUMER');


