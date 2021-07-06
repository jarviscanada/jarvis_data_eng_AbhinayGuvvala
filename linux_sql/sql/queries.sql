--Query 1: Group hosts by hardware info
SELECT
    cpu_number,
    id,
    total_mem
FROM
    (
        SELECT
            cpu_number,
            id,
            total_mem,
            RANK() OVER(
                PARTITION BY cpu_number
                ORDER BY
                    total_mem DESC
                )
        FROM
            host_info
    ) AS hardware_info_grouped_by_hosts;

--Query 2: Average memory usage
SELECT
    usage.host_id,
    info.hostname,
    date_trunc('hour', usage.timestamp) + date_part('minute', usage.timestamp):: int / 5 * interval '5 min' AS rounded_timestamp,
    AVG(
        ((info.total_mem - usage.memory_free)/ info.total_mem)* 100
        ) AS avg_memory_use_percentage
FROM
    host_usage usage
        LEFT JOIN host_info info ON usage.host_id = info.id
GROUP BY
    usage.host_id,
    info.hostname,
    rounded_timestamp
ORDER BY
    rounded_timestamp;

-- Query 3:Detect host failure
SELECT
    host_id,
    date_trunc('hour', timestamp) + date_part('minute', timestamp):: int / 5 * interval '5 min' AS rounded_timestamp,
    COUNT(*) AS num_data_points
FROM
    host_usage
GROUP BY
    host_id,
    rounded_timestamp
HAVING
    COUNT(*) < 3
ORDER BY
    rounded_timestamp;
