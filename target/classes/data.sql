DELETE FROM message;
INSERT INTO message (id, content, author, origin) VALUES
  (1, 'Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.', 'Albert Einstein', '2020-03-10 10:30:40'),
  (2, 'Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.', 'Albert Einstein', '2020-03-10 10:31:22'),
  (3, 'Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.', 'Mac Afee', '2020-03-10 10:38:11'),
  (4, 'Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis.', 'Tony Stark', '2020-03-10 10:42:57');

/* encrypted password for id 1..4 is 1234* */
DELETE FROM member;
INSERT INTO member (id, prename, lastname, password, username, authority, uses2FA, secret)
VALUES (1, 'Albert', 'Einstein', '/t2+zkzxhDgIumqVdCitszI4Ple5zKlPZNQa5W+wRlbj7UcmiKYOxA==', 'albert.einstein', 'admin', true, 'D5GUZ46UVWBZ5WBADLLHK2OAVLJ7OXJM'),
       (2, 'Mac', 'Afee', '/t2+zkzxhDgIumqVdCitszI4Ple5zKlPZNQa5W+wRlbj7UcmiKYOxA==', 'mac.afee', 'supervisor', true, 'D5GUZ46UVWBZ5WBADLLHK2OAVLJ7OXJM'),
       (3, 'Tony', 'Stark', '/t2+zkzxhDgIumqVdCitszI4Ple5zKlPZNQa5W+wRlbj7UcmiKYOxA==', 'toni.stark', 'moderator', true, 'D5GUZ46UVWBZ5WBADLLHK2OAVLJ7OXJM'),
       (4, 'Wilhelm', 'Tell', '/t2+zkzxhDgIumqVdCitszI4Ple5zKlPZNQa5W+wRlbj7UcmiKYOxA==', 'wilhelm.tell', 'admin' , true, 'D5GUZ46UVWBZ5WBADLLHK2OAVLJ7OXJM'),
       (5, 'Sinthi', 'abdu', 'wpzORQEDwHXqJETSZ5Ba6d1TNmxcF/+svQOJARNDkvtHw4dXj2Yxkw==', 'sinthi.abdu', 'admin' , true, '3LQH7NVL6CDIVCIC');
