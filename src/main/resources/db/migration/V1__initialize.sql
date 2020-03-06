-- USERS
CREATE TABLE public.users
(
    id       SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(20) NOT NULL,
    password VARCHAR NOT NULL
);

-- ROLES
CREATE TABLE public.roles
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL
);

-- NOTES
CREATE TABLE public.notes
(
    id SERIAL PRIMARY KEY NOT NULL,
    title TEXT NOT NULL,
    body TEXT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- USERS_NOTES
CREATE TABLE public.users_notes
(
    user_id INTEGER NOT NULL,
    note_id INTEGER NOT NULL,

    PRIMARY KEY (user_id,note_id),

    FOREIGN KEY (user_id) REFERENCES public.users (id)
        ON DELETE CASCADE ON UPDATE NO ACTION,

    FOREIGN KEY (note_id) REFERENCES public.notes (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- USERS_ROLES
CREATE TABLE public.users_roles
(
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,

    PRIMARY KEY (user_id,role_id),

    FOREIGN KEY (user_id) REFERENCES public.users (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION,

    FOREIGN KEY (role_id) REFERENCES public.roles (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- INSERTS
INSERT INTO public.users (username, password)
VALUES
('Bob', '$2a$10$P9E9Yp1vgAtCrj4viVaSk.PTR7kleLww1N6e1T0OK8rH0Tj6lN7wm'),
('Alex', '$2a$10$P9E9Yp1vgAtCrj4viVaSk.PTR7kleLww1N6e1T0OK8rH0Tj6lN7wm')
;
INSERT INTO public.roles (name)
VALUES
('ROLE_ADMIN'),
('ROLE_USER')
;
INSERT INTO public.notes (title, body)
VALUES
('Title 1', 'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...'),
('Title 2', 'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...'),
('Title 3', 'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...'),
('Title 4', 'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...')
;
INSERT INTO public.users_notes (user_id, note_id)
VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4)
;
INSERT INTO public.users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(2, 2)
;