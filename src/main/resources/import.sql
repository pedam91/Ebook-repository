insert into category(id, name) values (1, 'History');
insert into category(id, name) values (2, 'Love');
insert into category(id, name) values (3, 'Comics');

insert into language(id, name) values (1, 'English');
insert into language(id, name) values (2, 'Serbian');

insert into user(id, name, password, type, email, category_id) values (1, 'Petar Damjanović', 'pass1', 'ROLE_ADMIN', 'pedam91@gmail.com', null);
insert into user(id, name, password, type, email, category_id) values (2, 'Korisnik', 'pass1', 'ROLE_USER', 'korisnik@example.com', 2);

-- insert into book(id, title, author, keywords, publication_year, language_id, category_id, cataloguer_id) values (1, 'Koreni', 'Dobrica Ćosić', 'ninova nagrada', '1954', 2, 1, 1);

-- insert into file(id, file_name, mime) values (1, 'Knjiga1.pdf', 'application/pdf');

-- insert into book_files(book_id, files_id) values (1, 1);
