'use client';

import DOMPurify from 'dompurify';

export default function HtmlContent({ html }: { html: string }) {
    const safeHtml = DOMPurify.sanitize(html || '');

    return (
        <div
            className="html-content"
            dangerouslySetInnerHTML={{ __html: safeHtml }}
        />
    );
}