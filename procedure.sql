CREATE DEFINER=`root`@`localhost` PROCEDURE `add_movie`(  IN titles varchar(30),  IN years int(11), IN directors varchar(100),  IN starName varchar(100), IN genreName varchar(100),OUT statusMessage varchar(200) )
BEGIN
    SET @starId ='';
    SET @movieId='';
    SET @genreId='';
    SET @responseMessage='';

    SET @ExistsMovieId ='';
SELECT 
    @ExistsMovieId:=id
FROM
    movies
WHERE
    title = titles AND year = years
        AND director = directors
LIMIT 1;
    IF @ExistsMovieId =''
    THEN
        
        SET @responseMessage = 'Movie not found.';
        
        set @max_idTableExists='';
SELECT 
    @max_idTableExists:=table_name
FROM
    information_schema.tables
WHERE
    table_schema = 'moviedb'
        AND table_name = 'max_id'
LIMIT 1;
        IF @max_idTableExists = ''
        THEN
            CREATE TABLE max_id( id int(5) auto_increment primary key, tableName varchar(30), maxId int(10) );
        END IF;
        

        
        set @max_movieid_row_exists = '';
SELECT 
    @max_movieid_row_exists:=tableName
FROM
    max_id
WHERE
    tableName = 'movies'
LIMIT 1;


        IF @max_movieid_row_exists = ''
        THEN
            set @max_id_in_movies_table = '';
SELECT 
    @max_id_in_movies_table:=MAX(id)
FROM
    movies
ORDER BY id
LIMIT 1;

           


            SET @len = LENGTH(@max_id_in_movies_table);
            

            SET @table_value_in_int = SUBSTR(@max_id_in_movies_table,3,@len);
SELECT @table_value_in_int AS lenINT
;
            insert into max_id(tableName,maxId) values('movies',@table_value_in_int+1);
        END IF;


SELECT 
    @movieId:=maxId
FROM
    max_id
WHERE
    tableName = 'movies';
        SET @movieIdMax = @movieId +1;
        set @movieId = CONCAT('tt0',@movieId);
UPDATE max_id 
SET 
    maxId = @movieIdMax
WHERE
    tableName = 'movies';

    

    
        IF  starName != ''
        THEN 
            SELECT @starId:=id from stars where name = starName;
            IF @starId = ''
            THEN 

                set @max_starid_row_exists = '';
SELECT 
    @max_starid_row_exists:=tableName
FROM
    max_id
WHERE
    tableName = 'stars'
LIMIT 1;
                IF @max_starid_row_exists = ''
                THEN
                
                set @max_id_in_star_table = '';
SELECT 
    @max_id_in_star_table:=MAX(id)
FROM
    stars
ORDER BY id
LIMIT 1;





                



                set @max_id_star_int = CAST(SUBSTR(@max_id_in_star_table,3,LENGTH(@max_id_in_star_table)) as UNSIGNED)+1;



                insert into max_id(tableName,maxId) values('stars',@max_id_star_int);
                END IF;



SELECT 
    @starId:=maxId
FROM
    max_id
WHERE
    tableName = 'stars';
                SET @str = @starId;
                SET @starIdMax = @starId + 1;
                SET @starId = CONCAT('st',@starId);
UPDATE max_id 
SET 
    maxId = @starIdMax
WHERE
    tableName = 'stars';
                SET @responseMessage =CONCAT(@responseMessage,' Star not found. New Star insert');
                INSERT INTO stars values(CONCAT('nm',CAST(@str as UNSIGNED )+1),starName,0);
            END IF;
        END IF;


        IF  genreName != ''
        THEN 
            SELECT @genreId:=id from genres where name = genreName;
            IF @genreId = ''
            THEN 
                SET @responseMessage =CONCAT(@responseMessage,' Genre not found. New Genre Created');
                INSERT INTO genres(name) values(genreName);
               SET @genreId =LAST_INSERT_ID();
                
            END IF;
        END IF;


        SET @responseMessage='Movie added.';
        INSERT into movies(id,title,year,director) values(@movieId,titles,years,directors);

        
        

        SET @has_star_in_movies = '';
SELECT 
    @has_star_in_movies:=starId
FROM
    stars_in_movies
WHERE
    starID = @starID AND movieID = @movieId
LIMIT 1;
        IF @has_star_in_movies = ''
        THEN
            SET @responseMessage = CONCAT(@responseMessage,' New star_in_movies created.');
            SET @strId='';
SELECT 
    @strId:=id
FROM
    stars
WHERE
    name = starName
LIMIT 1;
            INSERT INTO stars_in_movies(starID,movieID) values(@strId,@movieId);
        END IF;


        SET @genre_in_movies ='';
SELECT 
    @genre_in_movies:=genreId
FROM
    genres_in_movies
WHERE
    genreId = @genreId
        AND movieID = @movieId;
        IF @genre_in_movies = ''
        THEN
            SET @responseMessage =CONCAT(@responseMessage,' New genres_in_movies created.');
            
            INSERT INTO genres_in_movies(genreId,MovieID) values(@genreId,@movieId);
        END IF;
        
        DROP TABLE max_id;
    
    ELSE
        SET @responseMessage =CONCAT(@responseMessage,' Movie already exists.');

    END IF;
    
    set statusMessage = @responseMessage;
    
    
END