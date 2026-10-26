# Staymmate delivery

The runnable source project is in the parent workspace folders:

- `backend/` — Spring Boot + JPA/MySQL REST service
- `frontend/` — React/Vite interface

Follow the root `README.md` for exact commands. The recommended first run uses the H2 development profile, then switch to MySQL by creating the `staymmate` database and setting the three `DB_*` variables.

Verified during delivery:

- `backend`: `mvn test -q` completed successfully.
- `frontend`: `npm install` and `npm run build` completed successfully.

The included demo data is for functional development. Production inventory must be obtained from owners or a licensed listing provider rather than copied or scraped from another housing site.
