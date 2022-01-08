create table if not exists flyway_schema_history
(
    installed_rank integer                 not null
        constraint flyway_schema_history_pk
            primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
);

create index if not exists flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table if not exists basenames
(
    id      uuid not null
        constraint basenames_pkey
            primary key,
    name    varchar(255),
    version integer
);

create index if not exists idx_basenames_name
    on basenames (name);

create table if not exists countries
(
    id             uuid not null
        constraint countries_pkey
            primary key,
    name           varchar(255)
        constraint uc_countries_name
            unique,
    iso3166_a2name varchar(2)
        constraint uc_countries_iso3166_a2name
            unique
);

create index if not exists idx_countries_name
    on countries (name);

create index if not exists idx_countries_iso3166_a2name
    on countries (iso3166_a2name);

create table if not exists language_locales
(
    id      uuid not null
        constraint language_locales_pkey
            primary key,
    version integer,
    locale  varchar(64)
        constraint uc_language_locales_locale
            unique
);

create table if not exists bundle_applications
(
    id                uuid not null
        constraint bundle_applications_pkey
            primary key,
    name              varchar(255)
        constraint idx_bundle_applications_name
            unique,
    version           integer,
    default_locale_id uuid
        constraint fk_bundle_applications_default_locale_id
            references language_locales
);

create table if not exists bundle_application_language_locales
(
    application_id      uuid not null
        constraint fk_bundle_application_id
            references bundle_applications,
    language_locales_id uuid not null
        constraint fk_bundle_application_language_locales_id
            references language_locales,
    constraint bundle_application_language_locales_pkey
        primary key (application_id, language_locales_id)
);

create table if not exists bundlenames
(
    id           uuid not null
        constraint bundlenames_pkey
            primary key,
    version      integer,
    filepath     varchar(4096),
    base_name_id uuid
        constraint fk_bundlenames_base_name_id
            references basenames,
    locale_id    uuid
        constraint fk_bundlenames_locale_id
            references language_locales,
    owner_id     uuid
        constraint fk_bundlenames_owner_id
            references bundle_applications
);

create index if not exists idx_bundlenames_base_name_id
    on bundlenames (base_name_id);

create index if not exists idx_bundlenames_filepath
    on bundlenames (filepath);

create index if not exists idx_bundlenames_locale_id
    on bundlenames (locale_id);

create index if not exists idx_bundlenames_owner_id
    on bundlenames (owner_id);

create index if not exists idx_language_locales_locale
    on language_locales (locale);

create table if not exists languages
(
    id       uuid not null
        constraint languages_pkey
            primary key,
    name     varchar(255)
        constraint uc_languages_name
            unique,
    version  integer,
    iso639_1 varchar(2)
        constraint uc_languages_iso639_1
            unique
);

create index if not exists idx_languages_name
    on languages (name);

create index if not exists idx_languages_iso639_1
    on languages (iso639_1);

create table if not exists properties_key_parts
(
    id        uuid not null
        constraint properties_key_parts_pkey
            primary key,
    depth     integer,
    node      boolean,
    value     text,
    parent_id uuid
        constraint fk_treeable_parent_id
            references properties_key_parts
);

create table if not exists properties_keys
(
    id      uuid not null
        constraint properties_keys_pkey
            primary key,
    name    varchar(255),
    version integer
);

create index if not exists idx_properties_keys_name
    on properties_keys (name);

create table if not exists properties_values
(
    id      uuid not null
        constraint properties_values_pkey
            primary key,
    name    varchar(255),
    version integer
);

create index if not exists idx_properties_values_name
    on properties_values (name);

create table if not exists resourcebundles
(
    id                  uuid not null
        constraint resourcebundles_pkey
            primary key,
    version             integer,
    bundlename_id       uuid
        constraint fk_resourcebundles_bundlename_id
            references bundlenames,
    properties_key_id   uuid
        constraint fk_resourcebundles_properties_key_id
            references properties_keys,
    properties_value_id uuid
        constraint fk_resourcebundles_properties_value_id
            references properties_values
);

create index if not exists idx_resourcebundles_bundlename_id
    on resourcebundles (bundlename_id);

create index if not exists idx_resourcebundles_properties_key_id
    on resourcebundles (properties_key_id);

create index if not exists idx_resourcebundles_properties_value_id
    on resourcebundles (properties_value_id);

