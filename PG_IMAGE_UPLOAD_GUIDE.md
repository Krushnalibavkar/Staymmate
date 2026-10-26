# Staymmate PG photo upload guide

## What to upload

Use a genuine, permitted photograph of the actual room. Choose simple small PG rooms: one plain bed for a single room, or two ordinary beds for a shared room. Do not use hotel, villa, farmhouse, or AI/stock photographs presented as real listings.

Save all photos as `.jpg`, under 1 MB where possible. The file name must match exactly (lowercase, hyphens, and `.jpg`).

## Exact photo names

| PG name | Put this exact file in `frontend/public/images/pg` |
|---|---|
| Cozy Nest Residency | cozy-nest-residency.jpg |
| Harbour View Homes | `harbour-view-homes.jpg` |
| Campus Comfort | `campus-comfort.jpg` |
| Western Heights PG | `western-heights-pg.jpg` |
| Deccan House | `deccan-house.jpg` |
| Metro Living | `metro-living.jpg` |
| Marine Residency | `marine-residency.jpg` |
| Powai Lake View | `powai-lake-view.jpg` |
| Thane Green Homes | `thane-green-homes.jpg` |
| Navi Mumbai Hub | `navi-mumbai-hub.jpg` |
| Nashik Student Stay | `nashik-student-stay.jpg` |
| Nagpur Central Rooms | `nagpur-central-rooms.jpg` |
| Kolhapur Comfort | `kolhapur-comfort.jpg` |
| Garden Residency | `garden-residency.jpg` |
| Sion Smart Stay | `sion-smart-stay.jpg` |
| Dadar Central PG | `dadar-central-pg.jpg` |
| Vile Parle Homes | `vile-parle-homes.jpg` |
| Mulund Metro Rooms | `mulund-metro-rooms.jpg` |
| Khar Comfort Living | `khar-comfort-living.jpg` |
| Malad Workstay | `malad-workstay.jpg` |
| Chembur Nest | `chembur-nest.jpg` |
| Airoli Professional PG | `airoli-professional-pg.jpg` |
| Panvel Student House | `panvel-student-house.jpg` |
| Kalyan City PG | `kalyan-city-pg.jpg` |
| Lonavala Hill Stay | `lonavala-hill-stay.jpg` |
| Aurangabad Scholars PG | `aurangabad-scholars-pg.jpg` |
| Solapur Shared Homes | `solapur-shared-homes.jpg` |
| Amravati Campus PG | `amravati-campus-pg.jpg` |
| Andheri Metro PG | `andheri-metro-pg.jpg` |
| Andheri East Co-Living | `andheri-east-co-living.jpg` |
| Lokhandwala Student PG | `lokhandwala-student-pg.jpg` |
| Bandra Station Residency | `bandra-station-residency.jpg` |
| Bandra West Shared Rooms | `bandra-west-shared-rooms.jpg` |
| Bandra Government Hostel | `bandra-government-hostel.jpg` |
| Borivali West PG | `borivali-west-pg.jpg` |
| Borivali Student Residence | `borivali-student-residence.jpg` |
| Kandivali Metro Rooms | `kandivali-metro-rooms.jpg` |
| Kandivali Government PG | `kandivali-government-pg.jpg` |
| Kandivali East Nest | `kandivali-east-nest.jpg` |

## Steps in Windows Explorer

1. Open this folder: `frontend/public/images/pg`.
2. Find the chosen PG in the table.
3. Rename its photo to the exact matching name from the table. For example, rename the Andheri Metro PG photo to `andheri-metro-pg.jpg`.
4. Copy or drag the renamed photo into the `pg` folder. Do not place it in `src` or `backend`.
5. Repeat for each PG.
6. Restart the backend so it stores the photo paths, then refresh the browser with `Ctrl+F5`.

If a photo is PNG or WEBP, either convert it to JPG first, or change the `.jpg` ending in both this guide and `AdditionalPgSeed.java` to the real extension.

