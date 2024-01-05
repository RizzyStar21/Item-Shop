 insert into Store (name, price, quantity)
	values ('Iron Rod', 10.99, 10),
			('Spoon', 9.99, 10),
			('Fire Gun', 20.99, 5),
			('Pickle Laser', 15.99, 6),
			('Teleporter', 66.99, 1),
			('Smart Watch', 24.99, 10),
			('The Thing', 5.99, 2),
			('Cookie Gun', 10.99, 5);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Tod', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);


insert into sec_role (roleName)
values ('ROLE_RICK');

insert into sec_role (roleName)
values ('ROLE_MORTY');
 
insert into user_role (userId, roleId)
values (1, 1);
 
insert into user_role (userId, roleId)
values (2, 2);
