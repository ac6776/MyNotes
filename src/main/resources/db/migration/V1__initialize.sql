SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id                    INT(11) NOT NULL AUTO_INCREMENT,
                       username              VARCHAR(50) NOT NULL,
                       password              CHAR(80) NOT NULL,
                       PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
                       id                    INT(11) NOT NULL AUTO_INCREMENT,
                       name                  VARCHAR(50) DEFAULT NULL,
                       PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS notes;

CREATE TABLE notes (
                       id                   INT(11) NOT NULL AUTO_INCREMENT,
                       title                VARCHAR(50) NOT NULL,
                       body                 TEXT NOT NULL,
                       creation_date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS users_notes;

CREATE TABLE users_notes (
                             user_id               INT(11) NOT NULL,
                             note_id               INT(11) NOT NULL,

                             PRIMARY KEY (user_id,note_id),

                             CONSTRAINT FK_USER_NOTE_ID FOREIGN KEY (user_id)
                                 REFERENCES users (id)
                                 ON DELETE NO ACTION ON UPDATE NO ACTION,
    #                                  ON DELETE CASCADE ON UPDATE NO ACTION,

                             CONSTRAINT FK_NOTE_ID FOREIGN KEY (note_id)
                                 REFERENCES notes (id)
                                 ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
                             user_id               INT(11) NOT NULL,
                             role_id               INT(11) NOT NULL,

                             PRIMARY KEY (user_id,role_id),

                             CONSTRAINT FK_USER_ID_01 FOREIGN KEY (user_id)
                                 REFERENCES users (id)
                                 ON DELETE NO ACTION ON UPDATE NO ACTION,

                             CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
                                 REFERENCES roles (id)
                                 ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO roles (name)
VALUES
('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username,password)
VALUES
('Alex','$2a$10$P9E9Yp1vgAtCrj4viVaSk.PTR7kleLww1N6e1T0OK8rH0Tj6lN7wm'),
('Bob', '$2a$10$P9E9Yp1vgAtCrj4viVaSk.PTR7kleLww1N6e1T0OK8rH0Tj6lN7wm');

INSERT INTO notes (title, body)
VALUES
('Title 1', 'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...'),
('Title 2', 'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...'),
('Title 3', 'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...'),
('Title 4', 'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(2, 1);

INSERT INTO users_notes (user_id, note_id)
VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4);