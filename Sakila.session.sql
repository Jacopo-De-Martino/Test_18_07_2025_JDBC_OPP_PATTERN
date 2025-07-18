SELECT title,
    release_year -- query che restituisci un ResultSet titolo e data di rilascio di tutti i film usciti dopo il 2005
FROM film
WHERE release_year > 2005
ORDER BY title ASC;
SELECT -- query he mestra l'id noleggio, data noleggio, data restituzione, e titolo del film.
    r.rental_id,
    r.rental_date,
    r.return_date,
    f.title AS film_title
FROM rental r
    JOIN customer c ON r.customer_id = c.customer_id
    JOIN inventory i ON r.inventory_id = i.inventory_id
    JOIN film f ON i.film_id = f.film_id
WHERE c.first_name = 'MARY'
    AND c.last_name = 'SMITH'
ORDER BY r.rental_date ASC;
SELECT f.title AS film_title,
    COUNT(r.rental_id) AS total_rentals
FROM film f
    JOIN inventory i ON f.film_id = i.film_id
    LEFT JOIN rental r ON i.inventory_id = r.inventory_id
GROUP BY f.title
ORDER BY total_rentals DESC,
    f.title ASC;
SELECT f.title AS film_title
FROM film f
    LEFT JOIN inventory i ON f.film_id = i.film_id
    LEFT JOIN rental r ON i.inventory_id = r.inventory_id
WHERE r.rental_id IS NULL
GROUP BY f.title
ORDER BY f.title ASC;
SELECT c.name AS category_name,
    COUNT(r.rental_id) AS total_rentals
FROM category c
    JOIN film_category fc ON c.category_id = fc.category_id
    JOIN film f ON fc.film_id = f.film_id
    JOIN inventory i ON f.film_id = i.film_id
    JOIN rental r ON i.inventory_id = r.inventory_id
GROUP BY c.name
ORDER BY total_rentals DESC
LIMIT 1;