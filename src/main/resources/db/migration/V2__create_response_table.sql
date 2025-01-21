


CREATE TABLE response (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message VARCHAR(255) NOT NULL,
    solution BOOLEAN DEFAULT FALSE,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    author_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    CONSTRAINT fk_response_author FOREIGN KEY (author_id) REFERENCES users (id),
    CONSTRAINT fk_response_topic FOREIGN KEY (topic_id) REFERENCES topics (id)
);
