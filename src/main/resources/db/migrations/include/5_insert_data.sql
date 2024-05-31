do 
'
DECLARE
    user_id bigint:=0;
    org_id bigint:=0;
    bran_id bigint:=0;
    organization_branch_id bigint:=0;
	r bigint;
BEGIN

    --Добавление пользователя
    select id into user_id from public.users where last_name = ''Срокисжигалова'';
    if user_id is null then
        INSERT INTO public.users (first_name, last_name, middle_name, birthday)
        VALUES (''Анна'', ''Срокисжигалова'', ''Игоревна'', ''25-04-1994'')
        RETURNING id INTO user_id;
    end if;
	
	FOR r IN
        SELECT * FROM generate_series(1, 100)
    LOOP
		select id into org_id from public.organization where short_name = ''Comp''||r;
    	if org_id is null then
	        INSERT INTO public.organization (name, short_name, inn, ogrn, address, general_director)
	        VALUES (''LongNameOfTheCompany''||r, ''Comp''||r, ''0000000000''||r, ''0000000000''||r, ''ул Организаторская д.''||r, user_id)
	        RETURNING id INTO org_id;
		end if;
		
		select id into bran_id from public.branch_office where name = ''Филиал №''||r;
    	if bran_id is null then
	        INSERT INTO public.branch_office  (name, mail, "header")
	        VALUES (''Филиал №''||r, ''branch@gmail.ru'', user_id)
	        RETURNING id INTO bran_id;
		end if;

		select id into organization_branch_id from public.organization_branch ob where ob.organization_id = org_id and ob.branch_id = bran_id;
    	if organization_branch_id is null then
	        INSERT INTO public.organization_branch (organization_id, branch_id)
	        VALUES (org_id, bran_id)
	        RETURNING id INTO organization_branch_id;
		end if;
			
    END LOOP;
    
end;
' LANGUAGE PLPGSQL;