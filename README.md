# Wallet
Through this service, you can read the records that were stored in the legacy archive system. In more detail, all records of the main archive table, file, image (imageInfo) and note table can be read via this service if they are included in the four below views. OpenAPI definition of this service is available in [dev environment](https://basamad-dev.gamelectronics.com/archive-reader/api/v1/swagger-ui/index.html). 
## Archive Reader Views
To add new archive to this service, tou need to create four view for the new archive and then use `union all` operation to add then to Archive Reader Views. These for views are `VW_ARCHIVE_READER_ARCHIVE`, `VW_ARCHIVE_READER_FILE`, `VW_ARCHIVE_READER_IMAGE` and `VW_ARCHIVE_READER_NOTE`.
### Archive Reader Views Examples
