-- Query 1: Seleziona tutti i film rilasciati dopo il 2005, ordinati per titolo in ordine alfabetico.
SELECT title,
    release_year
FROM film
WHERE release_year > 2005
ORDER BY title ASC;
---
-- Query 2: Mostra tutti i noleggi effettuati da un cliente di nome Mary Smith, inclusi l'ID noleggio, le date di noleggio e restituzione, e il titolo del film.
SELECT r.rental_id,
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
---
-- Query 3: Per ogni film, mostra il numero totale di volte in cui è stato noleggiato.
SELECT f.title AS film_title,
    COUNT(r.rental_id) AS total_rentals
FROM film f
    JOIN inventory i ON f.film_id = i.film_id
    LEFT JOIN rental r ON i.inventory_id = r.inventory_id
GROUP BY f.title
ORDER BY total_rentals DESC,
    f.title ASC;
---
-- Query 4: Elenca i nomi dei film che non sono mai stati noleggiati.
SELECT f.title AS film_title
FROM film f
    LEFT JOIN inventory i ON f.film_id = i.film_id
    LEFT JOIN rental r ON i.inventory_id = r.inventory_id
WHERE r.rental_id IS NULL
GROUP BY f.title
ORDER BY f.title ASC;
---
-- Query 5: Trova il genere (category) con il maggior numero di noleggi.
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