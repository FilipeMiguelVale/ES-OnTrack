import logging
import sys
from python_elastic_logstash import ElasticHandler, ElasticFormatter

"""
Provide logger name simple without any special character
Logger name will become as Elastic Search Index
"""
logger = logging.getLogger('esp23')
logger.setLevel(logging.DEBUG)

elasticsearch_endpoint = 'http://192.168.160.18:9200' # No trailing slash

elastic_handler = ElasticHandler(elasticsearch_endpoint, 'dev')  # Second argument is optional
elastic_handler.setFormatter(ElasticFormatter())

logger.addHandler(elastic_handler)

# Extra is optional.
extra = {
    'elastic_fields': {
        'version': 'python version: ' + repr(sys.version_info)
    }
}

#logger.debug("Getting data", extra=extra)
logger.debug("Getting data")
logger.debug("Getting data")
logger.debug("Getting data")
logger.debug("Getting data")
logger.debug("Getting data")
