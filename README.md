REST API:

Tag


  GET: /tags - get all tags;\r\n
	GET: /tags/id - get tag by id;
	POST: /tags - save tag. Tag should be sent using request-body in appropriate JSON-format;
	PUT: /tags - update tag. Tag should be sent using request-body in appropriate JSON-format;
	DELETE: /tags/id - delete tag by id.

Author

  GET: /authors - get all authors;
	GET: /authors/id - get author by id;
	POST: /authors - save author. Author should be sent using request-body in appropriate JSON-format;
	PUT: /authors - update author. Author should be sent using request-body in appropriate JSON-format;
	DELETE: /authors/id - delete author by id.

News

  GET: /news - get all news;
	GET: /news/id - get news by id;
	GET: /news/search?params=... - get news according to search conditions.
	POST: /news - save news. News should be sent using request-body in appropriate JSON-format;
	PUT: /news - save news. News should be sent using request-body in appropriate JSON-format;
	DELETE: /news/id - delete news by id.
	 
